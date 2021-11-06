package MoreClassRelics.relics.ironclad;

import MoreClassRelics.MoreClassRelicsMod;
import MoreClassRelics.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static MoreClassRelics.MoreClassRelicsMod.makeRelicOutlinePath;
import static MoreClassRelics.MoreClassRelicsMod.makeRelicPath;

public class BurningCoal extends CustomRelic {
    public static final String ID = MoreClassRelicsMod.makeID("BurningCoal");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("burning_coal.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("burning_coal_outline.png"));
    private boolean triggeredThisTurn = false;

    public void atTurnStart() {
        this.triggeredThisTurn = false;
    }

    public BurningCoal() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.HEAVY);
    }

    public void onExhaust(AbstractCard card) {
        if (!this.triggeredThisTurn) {
            this.triggeredThisTurn = true;
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new GainEnergyAction(1));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new BurningCoal();
    }
}
