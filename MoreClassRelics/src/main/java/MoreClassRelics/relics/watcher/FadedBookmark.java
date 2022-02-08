package MoreClassRelics.relics.watcher;

import MoreClassRelics.MoreClassRelicsMod;
import MoreClassRelics.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static MoreClassRelics.MoreClassRelicsMod.makeRelicOutlinePath;
import static MoreClassRelics.MoreClassRelicsMod.makeRelicPath;

public class FadedBookmark extends CustomRelic {
    public static final String ID = MoreClassRelicsMod.makeID("FadedBookmark");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("faded_bookmark.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("faded_bookmark_outline.png"));

    private int damageCounter = 0;

    public FadedBookmark() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.FLAT);
    }

    public void onPlayerEndTurn() {
        if (!AbstractDungeon.player.hand.group.isEmpty()) {
            for (AbstractCard card : AbstractDungeon.player.hand.group) {
                if (card.retain || card.selfRetain) {
                    damageCounter++;
                }
            }

            if (this.damageCounter > 0) {
                this.flash();
                this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                for (int i = 0; i < this.damageCounter; i++) {
                    this.addToBot(new DamageRandomEnemyAction(new DamageInfo((AbstractCreature)null, 2, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                }
                this.damageCounter = 0;
            }
        }
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new FadedBookmark();
    }
}
