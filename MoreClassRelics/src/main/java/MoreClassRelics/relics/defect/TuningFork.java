package MoreClassRelics.relics.defect;

import MoreClassRelics.DefaultMod;
import MoreClassRelics.powers.LoseFocusPower;
import MoreClassRelics.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static MoreClassRelics.DefaultMod.makeRelicOutlinePath;
import static MoreClassRelics.DefaultMod.makeRelicPath;

public class TuningFork extends CustomRelic {
    public static final String ID = DefaultMod.makeID("TuningFork");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("ghost_pepper.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("ghost_pepper.png"));

    public TuningFork() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    public void onEvokeOrb(AbstractOrb ammo) {
        this.flash();
        AbstractPlayer p = AbstractDungeon.player;
        this.addToBot(new RelicAboveCreatureAction(p, this));
        this.addToBot(new ApplyPowerAction(p, p, new FocusPower(p, 1), 1));
        this.addToBot(new ApplyPowerAction(p, p, new LoseFocusPower(p, 1), 1));
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new TuningFork();
    }
}
