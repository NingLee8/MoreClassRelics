package MoreClassRelics.powers;

import MoreClassRelics.MoreClassRelicsMod;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import java.util.Arrays;
import java.util.List;

public class CounterPower extends AbstractPower implements CloneablePowerInterface {
    public static final String POWER_ID = MoreClassRelicsMod.makeID("CounterPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final List<String> SFX = Arrays.asList("3A", "3B", "3C", "MA", "MB", "MC");

    public CounterPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.amount = amount;
        this.type = PowerType.DEBUFF;
        this.owner = owner;
        this.isTurnBased = false;

        this.loadRegion("anger");

        this.updateDescription();
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && damageAmount > 0 && !AbstractDungeon.player.isDead) {
            int roll = MathUtils.random(5);
            CardCrawlGame.sound.play("VO_MERCHANT_" + SFX.get(roll));
            this.flash();
            this.addToTop(new DamageAllEnemiesAction((AbstractCreature)null, DamageInfo.createDamageMatrix(damageAmount, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
            this.addToTop(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
        }

        return damageAmount;
    }

    public void updateDescription() {
        if (this.amount <= 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new CounterPower(this.owner, this.amount);
    }
}
