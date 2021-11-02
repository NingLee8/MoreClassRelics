package MoreClassRelics.patches.relics;

import MoreClassRelics.DefaultMod;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.watcher.MantraPower;
import javassist.CannotCompileException;
import javassist.CtBehavior;

import java.util.ArrayList;

@SpirePatch2(
        clz= ScryAction.class,
        method="update"
)
public class EmperorsAnkhPatch {

    @SpireInsertPatch(
            locator = Locator.class
    )
    public static void Insert() {
        if (AbstractDungeon.player.hasRelic(DefaultMod.makeID("EmperorsAnkh"))){
            int mantra = AbstractDungeon.gridSelectScreen.selectedCards.size();
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new MantraPower(AbstractDungeon.player, mantra), mantra));
        }
    }

    // ModTheSpire searches for a nested class that extends SpireInsertLocator
    // This class will be the Locator for the @SpireInsertPatch
    // When a Locator is not specified, ModTheSpire uses the default behavior for the @SpireInsertPatch
    private static class Locator extends SpireInsertLocator {
        // This is the abstract method from SpireInsertLocator that will be used to find the line
        // numbers you want this patch inserted at
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
            // finalMatcher is the line that we want to insert our patch before -
            // in this example we are using a `MethodCallMatcher` which is a type
            // of matcher that matches a method call based on the type of the calling
            // object and the name of the method being called. Here you can see that
            // we're expecting the `end` method to be called on a `SpireBatch`
            Matcher finalMatcher = new Matcher.MethodCallMatcher(ArrayList.class, "clear");

            // the `new ArrayList<Matcher>()` specifies the prerequisites before the line can be matched -
            // the LineFinder will search for all the prerequisites in order before it will match the finalMatcher -
            // since we didn't specify any prerequisites here, the LineFinder will simply find the first expression
            // that matches the finalMatcher.
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}
