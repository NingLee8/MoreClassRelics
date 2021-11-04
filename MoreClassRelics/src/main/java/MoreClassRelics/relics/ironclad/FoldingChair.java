package MoreClassRelics.relics.ironclad;

import MoreClassRelics.DefaultMod;
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

import static MoreClassRelics.DefaultMod.makeRelicOutlinePath;
import static MoreClassRelics.DefaultMod.makeRelicPath;

public class FoldingChair extends CustomRelic {
    public static final String ID = DefaultMod.makeID("FoldingChair");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("ghost_pepper.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("ghost_pepper.png"));

    public FoldingChair() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.SOLID);
    }

    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            FoldingChairPatch.currentHealthBeforeAttacked.set(monster, monster.currentHealth);
            FoldingChairPatch.triggeredFoldingChair.set(monster, false);
        }
    }

    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {

        if (!FoldingChairPatch.triggeredFoldingChair.get(target)) {
            Integer previousHealth = FoldingChairPatch.currentHealthBeforeAttacked.get(target);
            Integer newHealth = target.currentHealth - damageAmount;
            int totalDamageTaken = previousHealth - newHealth;

            boolean damageThresholdMet = (float)totalDamageTaken >= (float)target.maxHealth / 2.0F;
            boolean isNotMinion = !target.hasPower("Minion");
            boolean isNormalDamage = DamageInfo.DamageType.NORMAL.equals(info.type);
            if (info.owner != null && isNormalDamage && isNotMinion && damageThresholdMet) {
                this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                this.addToTop(new HealAction(AbstractDungeon.player, AbstractDungeon.player, 3, 0.0F));
                FoldingChairPatch.triggeredFoldingChair.set(target, true);
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
