package MoreClassRelics.patches.relics;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;

@SpirePatch2(clz= AbstractCard.class, method= SpirePatch.CLASS)
public class CreatedDuringCombatPatch {
    public static SpireField<Boolean> createdDuringCombat = new SpireField<Boolean>(() -> false);
}