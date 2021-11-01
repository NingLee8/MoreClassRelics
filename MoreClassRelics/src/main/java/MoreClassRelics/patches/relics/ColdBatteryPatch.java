package MoreClassRelics.patches.relics;

import MoreClassRelics.DefaultMod;
import com.evacipated.cardcrawl.modthespire.lib.*;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.Frost;
import com.megacrit.cardcrawl.orbs.Lightning;


 public class ColdBatteryPatch {
     @SpirePatch2(
             clz= Frost.class,
             method=SpirePatch.CONSTRUCTOR
     )
     public static class ColdBatteryFrostPatch {

         @SpirePostfixPatch
         public static void changeBaseAmounts(int ___passiveAmount, int ___evokeAmount) {
             if (AbstractDungeon.player.hasRelic(DefaultMod.makeID("ColdBattery"))) {
                 ___passiveAmount = 3;
                 ___evokeAmount = 6;
             }
         }
     }

     @SpirePatch2(
             clz= Lightning.class,
             method=SpirePatch.CONSTRUCTOR
     )
     public static class ColdBatteryLightningPatch {

         @SpirePostfixPatch
         public static void changeBaseAmounts(Lightning __instance, int ___passiveAmount, int ___evokeAmount) {
             if (AbstractDungeon.player.hasRelic(DefaultMod.makeID("ColdBattery"))) {
                 ___passiveAmount = 2;
                 ___evokeAmount = 7;
             }
         }
     }
 }

