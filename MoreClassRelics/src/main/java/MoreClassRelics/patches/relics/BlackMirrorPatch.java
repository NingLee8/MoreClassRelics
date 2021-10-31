package MoreClassRelics.patches.relics;

import MoreClassRelics.DefaultMod;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.common.DarkOrbEvokeAction;
import com.megacrit.cardcrawl.actions.defect.DarkImpulseAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.Dark;

@SpirePatch2(
        clz= Dark.class,
        method="onEvoke"
)
public class BlackMirrorPatch {

    @SpirePostfixPatch
    public static void triggerPassive() {
        if (AbstractDungeon.player.hasRelic(DefaultMod.makeID("BlackMirror"))) {
            AbstractDungeon.actionManager.addToTop(new DarkImpulseAction());
        }

    }
}
