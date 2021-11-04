package MoreClassRelics.relics.defect;

import MoreClassRelics.DefaultMod;
import MoreClassRelics.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static MoreClassRelics.DefaultMod.makeRelicOutlinePath;
import static MoreClassRelics.DefaultMod.makeRelicPath;

public class ColdBattery extends CustomRelic {
    public static final String ID = DefaultMod.makeID("ColdBattery");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("cold_battery.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("cold_battery_outline.png"));

    public ColdBattery() {
        super(ID, IMG, OUTLINE, RelicTier.SHOP, LandingSound.CLINK);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new ColdBattery();
    }
}
