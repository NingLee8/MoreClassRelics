package MoreClassRelics.relics.watcher;

import MoreClassRelics.MoreClassRelicsMod;
import MoreClassRelics.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static MoreClassRelics.MoreClassRelicsMod.makeRelicOutlinePath;
import static MoreClassRelics.MoreClassRelicsMod.makeRelicPath;

public class EmperorsAnkh extends CustomRelic {
    public static final String ID = MoreClassRelicsMod.makeID("EmperorsAnkh");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("emperors_ankh.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("emperors_ankh_outline.png"));

    public EmperorsAnkh() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new EmperorsAnkh();
    }
}
