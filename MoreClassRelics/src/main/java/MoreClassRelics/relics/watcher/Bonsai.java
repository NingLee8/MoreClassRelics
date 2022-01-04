package MoreClassRelics.relics.watcher;

import MoreClassRelics.MoreClassRelicsMod;
import MoreClassRelics.patches.relics.CreatedDuringCombatPatch;
import MoreClassRelics.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static MoreClassRelics.MoreClassRelicsMod.makeRelicOutlinePath;
import static MoreClassRelics.MoreClassRelicsMod.makeRelicPath;

public class Bonsai extends CustomRelic {
    public static final String ID = MoreClassRelicsMod.makeID("Bonsai");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("bonsai.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("bonsai_outline.png"));

    public Bonsai() {
        super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.MAGICAL);
    }

    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (CreatedDuringCombatPatch.createdDuringCombat.get(c)) {
            AbstractPlayer p = AbstractDungeon.player;
            this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 1), 1));
            this.addToBot(new ApplyPowerAction(p, p, new LoseStrengthPower(p, 1), 1));
            this.addToBot(new RelicAboveCreatureAction(p, this));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Bonsai();
    }
}
