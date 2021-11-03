package MoreClassRelics.patches.relics;

import MoreClassRelics.patches.relicinterfaces.BetterOnDiscardRelic;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import javassist.CtBehavior;

public class BetterOnDiscardRelicPatch {

    @SpirePatch2(clz = DiscardAction.class, method = "update")
    public static class BetterOnDiscardRelicActionPatch {
        @SpireInsertPatch(locator = BetterOnDiscardRelicPatch.Locator.class, localvars={"c"})
        public static void Insert(DiscardAction __instance, AbstractCard c) {
           Do(c);
        }
    }

    @SpirePatch2(clz = DiscardSpecificCardAction.class, method = "update")
    public static class BetterOnDiscardRelicSpecificCardActionPatch {
        @SpireInsertPatch(locator = BetterOnDiscardRelicPatch.Locator.class)
        public static void Insert(AbstractCard ___targetCard) {
            Do(___targetCard);
        }
    }

    private static class Locator extends SpireInsertLocator {
        private Locator() {}

        public int[] Locate(CtBehavior ctBehavior) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractCard.class, "triggerOnManualDiscard");
            return LineFinder.findAllInOrder(ctBehavior, finalMatcher);
        }
    }

    private static void Do(AbstractCard c) {
        for (AbstractRelic r : AbstractDungeon.player.relics) {
            if (r instanceof BetterOnDiscardRelic) {
                ((BetterOnDiscardRelic) r).betterOnDiscardRelic(c);
            }
        }
    }
}
