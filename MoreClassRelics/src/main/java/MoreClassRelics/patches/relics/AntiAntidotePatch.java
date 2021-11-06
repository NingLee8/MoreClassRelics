package MoreClassRelics.patches.relics;

import MoreClassRelics.MoreClassRelicsMod;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.actions.unique.PoisonLoseHpAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import javassist.CannotCompileException;
import javassist.CtBehavior;

@SpirePatch2(
        clz= PoisonLoseHpAction.class,
        method="update"
)
public class AntiAntidotePatch {

    @SpireInsertPatch(locator=Locator.class, localvars={"p"})
    public static void Insert(AbstractPower p) {
        if (AbstractDungeon.player.hasRelic(MoreClassRelicsMod.makeID("AntiAntidote"))){
           p.amount++;
        }
    }

    private static class Locator extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractPower.class, "amount");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}
