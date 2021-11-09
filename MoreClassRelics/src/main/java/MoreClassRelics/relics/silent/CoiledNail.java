package MoreClassRelics.relics.silent;

import MoreClassRelics.MoreClassRelicsMod;
import MoreClassRelics.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static MoreClassRelics.MoreClassRelicsMod.makeRelicOutlinePath;
import static MoreClassRelics.MoreClassRelicsMod.makeRelicPath;

public class CoiledNail extends CustomRelic {
    public static final String ID = MoreClassRelicsMod.makeID("CoiledNail");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("coiled_nail.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("coiled_nail_outline.png"));

    public CoiledNail() {
        super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.SOLID);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new CoiledNail();
    }
}
