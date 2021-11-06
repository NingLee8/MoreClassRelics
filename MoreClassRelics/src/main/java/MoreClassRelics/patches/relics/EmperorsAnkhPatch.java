package MoreClassRelics.patches.relics;

import MoreClassRelics.MoreClassRelicsMod;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
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
        if (AbstractDungeon.player.hasRelic(MoreClassRelicsMod.makeID("EmperorsAnkh"))){
            int mantra = AbstractDungeon.gridSelectScreen.selectedCards.size();
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new MantraPower(AbstractDungeon.player, mantra), mantra));
        }
    }

    private static class Locator extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(ArrayList.class, "clear");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}
