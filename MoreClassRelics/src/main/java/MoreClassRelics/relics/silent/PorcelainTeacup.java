package MoreClassRelics.relics.silent;

import MoreClassRelics.MoreClassRelicsMod;
import MoreClassRelics.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;


import static MoreClassRelics.MoreClassRelicsMod.makeRelicOutlinePath;
import static MoreClassRelics.MoreClassRelicsMod.makeRelicPath;

public class PorcelainTeacup extends CustomRelic {
    public static final String ID = MoreClassRelicsMod.makeID("PorcelainTeacup");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("porcelain_teacup.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("porcelain_teacup_outline.png"));

    private boolean shouldTriggerVFX = true;

    public PorcelainTeacup() {
        super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.CLINK);
    }

    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        shouldTriggerVFX = true;
    }

    public void onManualDiscard() {
        AbstractMonster randomMonster = AbstractDungeon.getMonsters().getRandomMonster((AbstractMonster)null, true, AbstractDungeon.cardRandomRng);
        if (!randomMonster.isDead && !randomMonster.isDying) {
            this.addToBot(new ApplyPowerAction(randomMonster, AbstractDungeon.player, new PoisonPower(randomMonster, AbstractDungeon.player, 1), 1));
            if (shouldTriggerVFX) {
                this.flash();
                this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                shouldTriggerVFX = false;
            }
        }
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new PorcelainTeacup();
    }
}
