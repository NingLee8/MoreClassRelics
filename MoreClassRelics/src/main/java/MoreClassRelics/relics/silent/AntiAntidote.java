package MoreClassRelics.relics.silent;

import MoreClassRelics.MoreClassRelicsMod;
import MoreClassRelics.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static MoreClassRelics.MoreClassRelicsMod.makeRelicOutlinePath;
import static MoreClassRelics.MoreClassRelicsMod.makeRelicPath;

public class AntiAntidote extends CustomRelic {
    public static final String ID = MoreClassRelicsMod.makeID("AntiAntidote");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("anti_antidote_nomist.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("anti_antidote_outline_nomist.png"));

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
