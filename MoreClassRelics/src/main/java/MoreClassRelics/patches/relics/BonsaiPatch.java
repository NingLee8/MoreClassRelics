package MoreClassRelics.patches.relics;

import com.evacipated.cardcrawl.modthespire.lib.*;

import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;
import javassist.CannotCompileException;
import javassist.CtBehavior;


public class BonsaiPatch {

    @SpirePatch2(clz= ShowCardAndAddToDrawPileEffect.class, method="update")
    public static class ShowCardAndAddToDrawPileEffectPatch {
        @SpireInsertPatch(locator= BonsaiPatch.DrawPileLocator.class)
        public static void setCreated(@ByRef AbstractCard[] ___card) {
            CreatedDuringCombatPatch.createdDuringCombat.set(___card[0], true);
        }
    }

    @SpirePatch2(clz= MakeTempCardInHandAction.class, method="makeNewCard")
    public static class TempCardInHandPatch {
        public static AbstractCard Postfix(AbstractCard __result, boolean ___sameUUID) {
            if (!___sameUUID) {
                CreatedDuringCombatPatch.createdDuringCombat.set(__result, true);
            }
            return __result;
        }
    }

    @SpirePatch2(clz= ShowCardAndAddToDiscardEffect.class, method="update")
    public static class ShowCardAndAddToDiscardEffectPatch {
        @SpireInsertPatch(locator= BonsaiPatch.DiscardPileLocator.class)
        public static void setCreated(@ByRef AbstractCard[] ___card) {
            CreatedDuringCombatPatch.createdDuringCombat.set(___card[0], true);
        }
    }


    private static class DrawPileLocator extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(ShowCardAndAddToDrawPileEffect.class, "randomSpot");
            return LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher);
        }
    }

    private static class DiscardPileLocator extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(ShowCardAndAddToDiscardEffect.class, "duration");
            return LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}

