package MoreClassRelics.patches.relics;

import MoreClassRelics.MoreClassRelicsMod;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import javassist.CannotCompileException;
import javassist.CtBehavior;

@SpirePatch2(clz= ApplyPowerAction.class, method="update")
public class PorcelainTeacupPatch {

    @SpireInsertPatch(
            locator = PorcelainTeacupPatch.Locator.class
    )
    public static void Insert(ApplyPowerAction __instance, AbstractPower ___powerToApply) {
        if (AbstractDungeon.player.hasRelic(MoreClassRelicsMod.makeID("PorcelainTeacup")) &&
                __instance.source != null &&
                __instance.source.isPlayer &&
                __instance.target != __instance.source &&
                ___powerToApply.ID.equals("Weakened") &&
                !__instance.target.hasPower("Artifact")) {
            AbstractDungeon.player.getRelic(MoreClassRelicsMod.makeID("PorcelainTeacup")).onTrigger(__instance.target);
        }
    }

    private static class Locator extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractPlayer.class, "hasRelic");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}
