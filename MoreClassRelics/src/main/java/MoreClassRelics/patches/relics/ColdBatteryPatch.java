package MoreClassRelics.patches.relics;

import MoreClassRelics.DefaultMod;
import com.evacipated.cardcrawl.modthespire.lib.*;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.Frost;
import com.megacrit.cardcrawl.orbs.Lightning;


 public class ColdBatteryPatch {
     @SpirePatch2(
             clz= Frost.class,
             method="updateDescription"
     )
     public static class ColdBatteryFrostPatch {

         @SpirePrefixPatch
         public static void changeBaseAmounts(@ByRef int[] ___basePassiveAmount, @ByRef int[] ___baseEvokeAmount) {
             if (AbstractDungeon.player.hasRelic(DefaultMod.makeID("ColdBattery"))) {
                 ___basePassiveAmount[0] = 3;
                 ___baseEvokeAmount[0] = 8;
             }
         }
     }

     @SpirePatch2(
             clz= Lightning.class,
             method="updateDescription"
     )
     public static class ColdBatteryLightningPatch {

         @SpirePrefixPatch
         public static void changeBaseAmounts(@ByRef int[] ___basePassiveAmount, @ByRef int[] ___baseEvokeAmount) {
             if (AbstractDungeon.player.hasRelic(DefaultMod.makeID("ColdBattery"))) {
                 ___basePassiveAmount[0] = 2;
                 ___baseEvokeAmount[0] = 5;
             }
         }
     }
 }

