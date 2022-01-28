package MoreClassRelics.patches.relics;

import MoreClassRelics.patches.relicinterfaces.BetterOnDiscardRelic;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.defect.ScrapeFollowUpAction;
import com.megacrit.cardcrawl.actions.unique.GamblingChipAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import javassist.CtBehavior;

import java.util.Arrays;

public class BetterOnDiscardRelicPatch {

    @SpirePatch2(clz = DiscardAction.class, method = "update")
    public static class iBetterOnDiscardRelicActionPatch {
        @SpireInsertPatch(locator = BetterOnDiscardRelicPatch.iLocator.class, localvars={"amount", "c"})
        public static void Insert(DiscardAction __instance, int ___amount, AbstractCard c) {
           Do(c, ___amount);
        }
    }

    @SpirePatch2(clz = DiscardSpecificCardAction.class, method = "update")
    public static class BetterOnDiscardRelicSpecificCardActionPatch {
        @SpireInsertPatch(locator = BetterOnDiscardRelicPatch.iLocator.class)
        public static void Insert(AbstractCard ___targetCard) {
            Do(___targetCard, 1);
        }
    }

    private static class iLocator extends SpireInsertLocator {
        private iLocator() {}

        public int[] Locate(CtBehavior ctBehavior) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractCard.class, "triggerOnManualDiscard");
            int[] tmp = LineFinder.findAllInOrder(ctBehavior, finalMatcher);
            return Arrays.copyOf(tmp, Math.min(2, tmp.length));
        }
    }

    @SpirePatch2(clz = DiscardAction.class, method = "update")
    public static class sizeBetterOnDiscardRelicActionPatch {
        @SpireInsertPatch(locator = BetterOnDiscardRelicPatch.sizeLocator.class, localvars={"c"})
        public static void Insert(DiscardAction __instance, AbstractCard c) {
            Do(c, AbstractDungeon.handCardSelectScreen.selectedCards.group.size());
        }
    }

    private static class sizeLocator extends SpireInsertLocator {
        private sizeLocator() {}

        public int[] Locate(CtBehavior ctBehavior) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractCard.class, "triggerOnManualDiscard");
            return Arrays.copyOfRange(LineFinder.findAllInOrder(ctBehavior, finalMatcher),2,3);
        }
    }


    @SpirePatch2(clz = GamblingChipAction.class, method = "update")
    public static class GamblingChipActionPatch {
        @SpireInsertPatch(locator = BetterOnDiscardRelicPatch.BasicLocator.class, localvars={"c"})
        public static void Insert(AbstractCard c) {
            Do(c, AbstractDungeon.handCardSelectScreen.selectedCards.group.size());
        }
    }

    @SpirePatch2(clz = ScrapeFollowUpAction.class, method = "update")
    public static class ScrapeFollowUpActionPatch {
        @SpireInsertPatch(locator = BetterOnDiscardRelicPatch.BasicLocator.class, localvars={"c"})
        public static void Insert(AbstractCard c) {
            Do(c, 1);
        }
    }

    private static class BasicLocator extends SpireInsertLocator {
        private BasicLocator() {}

        public int[] Locate(CtBehavior ctBehavior) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractCard.class, "triggerOnManualDiscard");
            return LineFinder.findAllInOrder(ctBehavior, finalMatcher);
        }
    }

    private static void Do(AbstractCard c, int total) {
        for (AbstractRelic r : AbstractDungeon.player.relics) {
            if (r instanceof BetterOnDiscardRelic) {
                ((BetterOnDiscardRelic) r).betterOnDiscardRelic(c, total);
            }
        }
    }
}
