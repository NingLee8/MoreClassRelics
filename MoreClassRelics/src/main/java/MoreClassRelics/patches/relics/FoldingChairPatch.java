package MoreClassRelics.patches.relics;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.core.AbstractCreature;

@SpirePatch2(clz= AbstractCreature.class, method= SpirePatch.CLASS)
public class FoldingChairPatch {
    public static SpireField<Integer> currentHealthBeforeAttacked = new SpireField<Integer>(() -> 0);
    public static SpireField<Boolean> triggeredFoldingChair = new SpireField<Boolean>(() -> false);
}


