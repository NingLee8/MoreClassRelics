package MoreClassRelics.relics.defect;

import MoreClassRelics.MoreClassRelicsMod;
import MoreClassRelics.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.OnChannelRelic;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static MoreClassRelics.MoreClassRelicsMod.makeRelicOutlinePath;
import static MoreClassRelics.MoreClassRelicsMod.makeRelicPath;

public class GlassCog extends CustomRelic implements OnChannelRelic {
    public static final String ID = MoreClassRelicsMod.makeID("GlassCog");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("glass_cog.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("glass_cog_outline.png"));

    public GlassCog() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.CLINK);
    }

    public void atTurnStartPostDraw() {
        if (AbstractDungeon.player.filledOrbCount() == 0) {
            this.beginLongPulse();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new DrawCardAction(AbstractDungeon.player, 1));
        }
    }

    public void onEvokeOrb(AbstractOrb ammo){
        if (AbstractDungeon.player.filledOrbCount() == 1) {
            this.beginLongPulse();
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

    @Override
    public void onChannel(AbstractOrb abstractOrb) {
        this.stopPulse();
    }
}
