package MoreClassRelics.relics.silent;

import MoreClassRelics.MoreClassRelicsMod;
import MoreClassRelics.patches.relicinterfaces.BetterOnDiscardRelic;
import MoreClassRelics.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static MoreClassRelics.MoreClassRelicsMod.makeRelicOutlinePath;
import static MoreClassRelics.MoreClassRelicsMod.makeRelicPath;

public class Windchimes extends CustomRelic implements BetterOnDiscardRelic {
    public static final String ID = MoreClassRelicsMod.makeID("Windchimes");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("windchimes.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("windchimes_outline.png"));

    public Windchimes() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Windchimes();
    }

    @Override
    public void betterOnDiscardRelic(AbstractCard card) {
        if (AbstractCard.CardType.CURSE.equals(card.type)) {
            this.flash();
            CardCrawlGame.sound.play("RELIC_DROP_MAGICAL");
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 1), 1));
        }
    }
}
