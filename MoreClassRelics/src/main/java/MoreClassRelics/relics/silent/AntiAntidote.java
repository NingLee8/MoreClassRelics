package MoreClassRelics.relics.silent;

import MoreClassRelics.DefaultMod;
import MoreClassRelics.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static MoreClassRelics.DefaultMod.makeRelicOutlinePath;
import static MoreClassRelics.DefaultMod.makeRelicPath;

public class AntiAntidote extends CustomRelic {
    public static final String ID = DefaultMod.makeID("AntiAntidote");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("anti_antidote.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("anti_antidote_outline.png"));

    public AntiAntidote() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.CLINK);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new AntiAntidote();
    }
}
