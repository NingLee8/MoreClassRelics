package MoreClassRelics.patches.relics;

import MoreClassRelics.MoreClassRelicsMod;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

@SpirePatch2(
        clz= MakeTempCardInHandAction.class,
        method="update"
)
public class CoiledNailPatch {

    public static void Prefix(@ByRef int[] ___amount, AbstractCard ___c) {
        if (AbstractDungeon.player.hasRelic(MoreClassRelicsMod.makeID("CoiledNail"))) {
            if ("Shiv".equals(___c.cardID)) {
                ___amount[0]++;
            }
        }
    }
}
