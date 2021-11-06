package MoreClassRelics.relics.ironclad;

import MoreClassRelics.DefaultMod;
import MoreClassRelics.powers.CounterPower;
import MoreClassRelics.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.BufferPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import java.util.Arrays;
import java.util.List;

import static MoreClassRelics.DefaultMod.makeRelicOutlinePath;
import static MoreClassRelics.DefaultMod.makeRelicPath;

public class JackInTheBox extends CustomRelic {
    public static final String ID = DefaultMod.makeID("JackInTheBox");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("ghost_pepper.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("ghost_pepper.png"));
    private boolean triggeredThisTurn = false;

    private static final List<String> SFX = Arrays.asList("3A", "3B", "3C", "MA", "MB", "MC");

    public JackInTheBox() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.HEAVY);
    }

    public void onEquip() {
        this.counter = 0;
    }

    public void atTurnStart() {
        if (this.counter == -1) {
            this.counter += 2;
        } else {
            ++this.counter;
        }

        if (this.counter == 3) {
            AbstractPlayer p = AbstractDungeon.player;
            this.counter = 0;
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new ApplyPowerAction(p, p, new CounterPower(p, 1), 1));
        }

    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new JackInTheBox();
    }


}
