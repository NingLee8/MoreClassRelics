package MoreClassRelics.relics.ironclad;

import MoreClassRelics.MoreClassRelicsMod;
import MoreClassRelics.patches.relics.FoldingChairPatch;
import MoreClassRelics.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
import com.megacrit.cardcrawl.vfx.SpotlightPlayerEffect;

import static MoreClassRelics.MoreClassRelicsMod.makeRelicOutlinePath;
import static MoreClassRelics.MoreClassRelicsMod.makeRelicPath;

public class FoldingChair extends CustomRelic {
    public static final String ID = MoreClassRelicsMod.makeID("FoldingChair");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("folding_chair.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("folding_chair_outline.png"));

    private boolean shouldTriggerSpotlight = true;

    public FoldingChair() {
        super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.HEAVY);
    }

    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        shouldTriggerSpotlight = true;
    }

    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        boolean isFatal = target.currentHealth <= damageAmount;
        boolean isNormalDamage = DamageInfo.DamageType.NORMAL.equals(info.type);
        if (info.owner != null && isNormalDamage && isFatal) {
            FoldingChairPatch.triggeredFoldingChair.set(target, true);
        }
    }

    public void onMonsterDeath(AbstractMonster m) {
        if (FoldingChairPatch.triggeredFoldingChair.get(m)) {
            if ((m.isDying || m.currentHealth <= 0) && !m.halfDead && !m.hasPower("Minion")) {
                if (m.hasPower("Vulnerable")) {
                    AbstractDungeon.effectList.add(new RainingGoldEffect(12 * 2, true));
                    AbstractDungeon.player.gainGold(18);

                    if (shouldTriggerSpotlight) {
                        this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                        AbstractDungeon.effectsQueue.add(new SpotlightPlayerEffect());
                        shouldTriggerSpotlight = false;
                    }
                }
            }
        }
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new FoldingChair();
    }


}
