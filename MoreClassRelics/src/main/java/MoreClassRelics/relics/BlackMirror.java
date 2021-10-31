package MoreClassRelics.relics;

import MoreClassRelics.DefaultMod;
import MoreClassRelics.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.defect.DarkImpulseAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static MoreClassRelics.DefaultMod.makeRelicOutlinePath;
import static MoreClassRelics.DefaultMod.makeRelicPath;

public class BlackMirror extends CustomRelic {
    public static final String ID = DefaultMod.makeID("BlackMirror");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("ghost_pepper.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("ghost_pepper.png"));

    public BlackMirror() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    @Override
    public void onEvokeOrb(AbstractOrb ammo) {
        super.onEvokeOrb(ammo);
        if ("Dark".equals(ammo.ID)) {
            AbstractDungeon.actionManager.addToTop(new DarkImpulseAction());
        }
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new BlackMirror();
    }
}
