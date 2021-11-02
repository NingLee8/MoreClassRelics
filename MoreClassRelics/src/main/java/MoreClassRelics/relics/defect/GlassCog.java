package MoreClassRelics.relics.defect;

import MoreClassRelics.DefaultMod;
import MoreClassRelics.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.defect.DarkImpulseAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static MoreClassRelics.DefaultMod.makeRelicOutlinePath;
import static MoreClassRelics.DefaultMod.makeRelicPath;

public class GlassCog extends CustomRelic {
    public static final String ID = DefaultMod.makeID("GlassCog");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("ghost_pepper.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("ghost_pepper.png"));

    public GlassCog() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    public void atTurnStartPostDraw() {
        if (AbstractDungeon.player.filledOrbCount() == 0) {
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new DrawCardAction(AbstractDungeon.player, 2));
        }
    }

    public void onUsePotion() {
        this.updatePulse();
    }

    public void onPlayerEndTurn() {
        this.updatePulse();
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        this.updatePulse();
        return super.onAttacked(info, damageAmount);
    }

    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        this.updatePulse();
    }

    private void updatePulse() {
        if (AbstractDungeon.player.filledOrbCount() == 0) {
            this.beginLongPulse();
        } else {
            this.stopPulse();
        }
    }

    public void onVictory() {
        this.stopPulse();
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new GlassCog();
    }
}
