package MoreClassRelics.relics.defect;

import MoreClassRelics.MoreClassRelicsMod;
import MoreClassRelics.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.defect.DarkImpulseAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static MoreClassRelics.MoreClassRelicsMod.makeRelicOutlinePath;
import static MoreClassRelics.MoreClassRelicsMod.makeRelicPath;

public class BlackMirror extends CustomRelic {
    public static final String ID = MoreClassRelicsMod.makeID("BlackMirror");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("black_mirror.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("black_mirror_outline.png"));

    public BlackMirror() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.HEAVY);
    }

    public void onEvokeOrb(AbstractOrb ammo) {
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
