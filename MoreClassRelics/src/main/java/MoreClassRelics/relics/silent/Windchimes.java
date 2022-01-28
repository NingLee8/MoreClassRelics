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
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static MoreClassRelics.MoreClassRelicsMod.makeRelicOutlinePath;
import static MoreClassRelics.MoreClassRelicsMod.makeRelicPath;

public class Windchimes extends CustomRelic implements BetterOnDiscardRelic {
    public static final String ID = MoreClassRelicsMod.makeID("Windchimes");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("windchimes.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("windchimes_outline.png"));

    private boolean triggerEffects = true;
    private int triggerCounter = 0;
    private int cardCounter = 0;

    public Windchimes() {
        super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Windchimes();
    }

    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        triggerEffects = true;
        triggerCounter = 0;
        cardCounter = 0;
    }

    public void onUsePotion() {
        triggerEffects = true;
        triggerCounter = 0;
        cardCounter = 0;
    }

    @Override
    public void betterOnDiscardRelic(AbstractCard card, int total) {
        boolean triggerOnThisCard = false;
        cardCounter++;
        if (AbstractCard.CardType.CURSE.equals(card.type)) {
            triggerCounter++;
            triggerOnThisCard = true;
        }
        if (cardCounter >= total && triggerCounter > 0) {
            if (triggerEffects) {
                this.flash();
                this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                CardCrawlGame.sound.play("RELIC_DROP_MAGICAL");
                triggerEffects = false;
            }
            if (cardCounter > total && triggerOnThisCard) {
                this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 1), 1));
            } else if (cardCounter == total){
                this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, triggerCounter), triggerCounter));
            }

        }
    }
}
