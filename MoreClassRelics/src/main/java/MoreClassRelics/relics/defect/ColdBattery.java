package MoreClassRelics.relics.defect;

import MoreClassRelics.MoreClassRelicsMod;
import MoreClassRelics.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static MoreClassRelics.MoreClassRelicsMod.makeRelicOutlinePath;
import static MoreClassRelics.MoreClassRelicsMod.makeRelicPath;

public class ColdBattery extends CustomRelic {
    public static final String ID = MoreClassRelicsMod.makeID("ColdBattery");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("cold_battery.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("cold_battery_outline.png"));

    public ColdBattery() {
        super(ID, IMG, OUTLINE, RelicTier.SHOP, LandingSound.CLINK);
    }

    public void onPlayerEndTurn() {
        int lightning = 0;
        int frost = 0;
        for (AbstractOrb orb : AbstractDungeon.player.orbs) {
            if("Lightning".equals(orb.ID)) {
                lightning++;
            }
            if("Frost".equals(orb.ID)) {
                frost++;
            }
        }
        if (lightning > frost && frost > 0) {
            this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            for(AbstractOrb orb : AbstractDungeon.player.orbs) {
                if ("Frost".equals(orb.ID)) {
                    orb.onEndOfTurn();
                }
            }
        }
        else if (frost > lightning & lightning > 0) {
            this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            for(AbstractOrb orb : AbstractDungeon.player.orbs) {
                if ("Lightning".equals(orb.ID)) {
                    orb.onEndOfTurn();
                }
            }
        }
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
