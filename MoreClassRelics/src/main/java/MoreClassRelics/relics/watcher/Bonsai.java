package MoreClassRelics.relics.watcher;

import MoreClassRelics.DefaultMod;
import MoreClassRelics.patches.relics.CreatedDuringCombatPatch;
import MoreClassRelics.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static MoreClassRelics.DefaultMod.makeRelicOutlinePath;
import static MoreClassRelics.DefaultMod.makeRelicPath;

public class Bonsai extends CustomRelic {
    public static final String ID = DefaultMod.makeID("Bonsai");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("ghost_pepper.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("ghost_pepper.png"));

    public Bonsai() {
        super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.MAGICAL);
    }

    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (CreatedDuringCombatPatch.createdDuringCombat.get(c)) {
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 1), 1));
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new LoseStrengthPower(AbstractDungeon.player, 1), 1));
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
