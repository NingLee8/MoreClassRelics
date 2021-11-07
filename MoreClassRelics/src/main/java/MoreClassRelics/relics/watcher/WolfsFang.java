package MoreClassRelics.relics.watcher;

import MoreClassRelics.MoreClassRelicsMod;
import MoreClassRelics.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static MoreClassRelics.MoreClassRelicsMod.makeRelicOutlinePath;
import static MoreClassRelics.MoreClassRelicsMod.makeRelicPath;

public class WolfsFang extends CustomRelic {
    public static final String ID = MoreClassRelicsMod.makeID("WolfsFang");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("wolfs_fang.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("wolfs_fang_outline.png"));

    public WolfsFang() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void atTurnStartPostDraw() {
        if (AbstractDungeon.player.stance.ID.equals("Wrath")) {
            this.addToBot(new DrawCardAction(AbstractDungeon.player, 1));
        }
    }

    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (AbstractDungeon.player.stance.ID.equals("Wrath")) {
            this.beginLongPulse();
        } else {
            this.stopPulse();
        }
    }

    public void onVictory() {
        this.stopPulse();
    }

    @Override
    public AbstractRelic makeCopy() {
        return new WolfsFang();
    }
}
