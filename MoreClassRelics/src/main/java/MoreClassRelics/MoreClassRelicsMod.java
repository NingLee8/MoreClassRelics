package MoreClassRelics;

import MoreClassRelics.relics.defect.BlackMirror;
import MoreClassRelics.relics.defect.ColdBattery;
import MoreClassRelics.relics.defect.GlassCog;
import MoreClassRelics.relics.defect.TuningFork;
import MoreClassRelics.relics.ironclad.Emberfly;
import MoreClassRelics.relics.ironclad.FoldingChair;
import MoreClassRelics.relics.ironclad.GhostPepper;
import MoreClassRelics.relics.ironclad.JackInTheBox;
import MoreClassRelics.relics.silent.AntiAntidote;
import MoreClassRelics.relics.silent.CoiledNail;
import MoreClassRelics.relics.silent.PorcelainTeacup;
import MoreClassRelics.relics.silent.Windchimes;
import MoreClassRelics.relics.watcher.Bonsai;
import MoreClassRelics.relics.watcher.FadedBookmark;
import MoreClassRelics.relics.watcher.EmperorsAnkh;
import MoreClassRelics.relics.watcher.WolfsFang;
import basemod.*;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import MoreClassRelics.util.IDCheckDontTouchPls;
import MoreClassRelics.util.TextureLoader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@SpireInitializer
public class MoreClassRelicsMod implements
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        PostInitializeSubscriber {
    // Make sure to implement the subscribers *you* are using (read basemod wiki). Editing cards? EditCardsSubscriber.
    // Making relics? EditRelicsSubscriber. etc., etc., for a full list and how to make your own, visit the basemod wiki.
    public static final Logger logger = LogManager.getLogger(MoreClassRelicsMod.class.getName());
    private static String modID;

    // Mod-settings settings. This is if you want an on/off savable button
    public static Properties defaultSettings = new Properties();
    public static final String ENABLE_IRONCLAD_RELICS = "enableIroncladRelics";
    public static final String ENABLE_SILENT_RELICS = "enableSilentRelics";
    public static final String ENABLE_DEFECT_RELICS = "enableDefectRelics";
    public static final String ENABLE_WATCHER_RELICS = "enableWatcherRelics";
    public static boolean enableIroncladRelics = true;
    public static boolean enableSilentRelics = true;
    public static boolean enableDefectRelics = true;
    public static boolean enableWatcherRelics = true;

    //This is for the in-game mod settings panel.
    private static final String MODNAME = "More Class Relics";
    private static final String AUTHOR = "TheMonji";
    private static final String DESCRIPTION = "Mix of new class specific relics";

    //Mod Badge - A small icon that appears in the mod settings menu next to your mod.
    public static final String BADGE_IMAGE = "MoreClassRelicsResources/images/ghost_pepper_badge.png";
    
    // =============== MAKE IMAGE PATHS =================

    public static String makeRelicPath(String resourcePath) {
        return getModID() + "Resources/images/relics/" + resourcePath;
    }
    
    public static String makeRelicOutlinePath(String resourcePath) {
        return getModID() + "Resources/images/relics/outline/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return getModID() + "Resources/images/powers/" + resourcePath;
    }

    
    public MoreClassRelicsMod() {
        logger.info("Subscribe to BaseMod hooks");
        
        BaseMod.subscribe(this);

        setModID("MoreClassRelics");

        
        logger.info("Done subscribing");
        
        logger.info("Adding mod settings");
        // This loads the mod settings.
        // The actual mod Button is added below in receivePostInitialize()
        defaultSettings.setProperty(ENABLE_IRONCLAD_RELICS, "TRUE");
        defaultSettings.setProperty(ENABLE_SILENT_RELICS, "TRUE");
        defaultSettings.setProperty(ENABLE_DEFECT_RELICS, "TRUE");
        defaultSettings.setProperty(ENABLE_WATCHER_RELICS, "TRUE");

        try {
            SpireConfig config = new SpireConfig(MODNAME, getModID() + "Config", defaultSettings);
            config.load();
            enableIroncladRelics = config.getBool(ENABLE_IRONCLAD_RELICS);
            enableSilentRelics = config.getBool(ENABLE_SILENT_RELICS);
            enableDefectRelics = config.getBool(ENABLE_DEFECT_RELICS);
            enableWatcherRelics = config.getBool(ENABLE_WATCHER_RELICS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("Done adding mod settings");
        
    }
    
    // ====== NO EDIT AREA ======
    // DON'T TOUCH THIS STUFF. IT IS HERE FOR STANDARDIZATION BETWEEN MODS AND TO ENSURE GOOD CODE PRACTICES.
    // IF YOU MODIFY THIS I WILL HUNT YOU DOWN AND DOWNVOTE YOUR MOD ON WORKSHOP
    
    public static void setModID(String ID) { // DON'T EDIT
        Gson coolG = new Gson(); // EY DON'T EDIT THIS
        //   String IDjson = Gdx.files.internal("IDCheckStringsDONT-EDIT-AT-ALL.json").readString(String.valueOf(StandardCharsets.UTF_8)); // i hate u Gdx.files
        InputStream in = MoreClassRelicsMod.class.getResourceAsStream("/IDCheckStringsDONT-EDIT-AT-ALL.json"); // DON'T EDIT THIS ETHER
        IDCheckDontTouchPls EXCEPTION_STRINGS = coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class); // OR THIS, DON'T EDIT IT
        logger.info("You are attempting to set your mod ID as: " + ID); // NO WHY
        if (ID.equals(EXCEPTION_STRINGS.DEFAULTID)) { // DO *NOT* CHANGE THIS ESPECIALLY, TO EDIT YOUR MOD ID, SCROLL UP JUST A LITTLE, IT'S JUST ABOVE
            throw new RuntimeException(EXCEPTION_STRINGS.EXCEPTION); // THIS ALSO DON'T EDIT
        } else if (ID.equals(EXCEPTION_STRINGS.DEVID)) { // NO
            modID = EXCEPTION_STRINGS.DEFAULTID; // DON'T
        } else { // NO EDIT AREA
            modID = ID; // DON'T WRITE OR CHANGE THINGS HERE NOT EVEN A LITTLE
        } // NO
        logger.info("Success! ID is " + modID); // WHY WOULD U WANT IT NOT TO LOG?? DON'T EDIT THIS.
    } // NO
    
    public static String getModID() { // NO
        return modID; // DOUBLE NO
    } // NU-UH
    
    private static void pathCheck() { // ALSO NO
        Gson coolG = new Gson(); // NOPE DON'T EDIT THIS
        //   String IDjson = Gdx.files.internal("IDCheckStringsDONT-EDIT-AT-ALL.json").readString(String.valueOf(StandardCharsets.UTF_8)); // i still hate u btw Gdx.files
        InputStream in = MoreClassRelicsMod.class.getResourceAsStream("/IDCheckStringsDONT-EDIT-AT-ALL.json"); // DON'T EDIT THISSSSS
        IDCheckDontTouchPls EXCEPTION_STRINGS = coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class); // NAH, NO EDIT
        String packageName = MoreClassRelicsMod.class.getPackage().getName(); // STILL NO EDIT ZONE
        FileHandle resourcePathExists = Gdx.files.internal(getModID() + "Resources"); // PLEASE DON'T EDIT THINGS HERE, THANKS
        if (!modID.equals(EXCEPTION_STRINGS.DEVID)) { // LEAVE THIS EDIT-LESS
            if (!packageName.equals(getModID())) { // NOT HERE ETHER
                throw new RuntimeException(EXCEPTION_STRINGS.PACKAGE_EXCEPTION + getModID()); // THIS IS A NO-NO
            } // WHY WOULD U EDIT THIS
            if (!resourcePathExists.exists()) { // DON'T CHANGE THIS
                throw new RuntimeException(EXCEPTION_STRINGS.RESOURCE_FOLDER_EXCEPTION + getModID() + "Resources"); // NOT THIS
            }// NO
        }// NO
    }// NO

    public static void initialize() {
        logger.info("========================= Initializing Default Mod. Hi. =========================");
        MoreClassRelicsMod defaultmod = new MoreClassRelicsMod();
        logger.info("========================= /Default Mod Initialized. Hello World./ =========================");
    }

    @Override
    public void receivePostInitialize() {
        logger.info("Loading badge image and mod options");

        Texture badgeTexture = TextureLoader.getTexture(BADGE_IMAGE);

        ModPanel settingsPanel = new ModPanel();

        ModLabeledToggleButton enableIroncladRelicsButton = new ModLabeledToggleButton("Enable Ironclad Relics",
                350.0f, 700.0f, Settings.RED_RELIC_COLOR, FontHelper.charDescFont,
                enableIroncladRelics, settingsPanel, (label) -> {},
                (button) -> {
            enableIroncladRelics = button.enabled;
            try {
                SpireConfig config = new SpireConfig(MODNAME, getModID() + "Config", defaultSettings);
                config.setBool(ENABLE_IRONCLAD_RELICS, enableIroncladRelics);
                config.save();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ModLabeledToggleButton enableSilentRelicsButton = new ModLabeledToggleButton("Enable Silent Relics",
                350.0f, 660.0f, Settings.GREEN_RELIC_COLOR, FontHelper.charDescFont,
                enableSilentRelics, settingsPanel, (label) -> {},
                (button) -> {
                    enableSilentRelics = button.enabled;
                    try {
                        SpireConfig config = new SpireConfig(MODNAME, getModID() + "Config", defaultSettings);
                        config.setBool(ENABLE_SILENT_RELICS, enableSilentRelics);
                        config.save();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        ModLabeledToggleButton enableDefectRelicsButton = new ModLabeledToggleButton("Enable Defect Relics",
                350.0f, 620.0f, Settings.BLUE_RELIC_COLOR, FontHelper.charDescFont,
                enableDefectRelics, settingsPanel, (label) -> {},
                (button) -> {
                    enableDefectRelics = button.enabled;
                    try {
                        SpireConfig config = new SpireConfig(MODNAME, getModID() + "Config", defaultSettings);
                        config.setBool(ENABLE_DEFECT_RELICS, enableDefectRelics);
                        config.save();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        ModLabeledToggleButton enableWatcherRelicsButton = new ModLabeledToggleButton("Enable Watcher Relics",
                350.0f, 580.0f, Settings.PURPLE_RELIC_COLOR, FontHelper.charDescFont,
                enableWatcherRelics, settingsPanel, (label) -> {},
                (button) -> {
                    enableWatcherRelics = button.enabled;
                    try {
                        SpireConfig config = new SpireConfig(MODNAME, getModID() + "Config", defaultSettings);
                        config.setBool(ENABLE_WATCHER_RELICS, enableWatcherRelics);
                        config.save();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

        settingsPanel.addUIElement(new ModLabel("Must restart game to take effect", 350.0F, 750.0F, settingsPanel, me -> {}));
        settingsPanel.addUIElement(enableIroncladRelicsButton);
        settingsPanel.addUIElement(enableSilentRelicsButton);
        settingsPanel.addUIElement(enableDefectRelicsButton);
        settingsPanel.addUIElement(enableWatcherRelicsButton);

        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);

        logger.info("Done loading badge Image and mod options");
    }
    
    // =============== / POST-INITIALIZE/ =================

    
    // ================ ADD RELICS ===================
    
    @Override
    public void receiveEditRelics() {
        logger.info("Adding relics");

        if (enableIroncladRelics) {
            BaseMod.addRelic(new GhostPepper(), RelicType.RED);
            BaseMod.addRelic(new JackInTheBox(), RelicType.RED);
            BaseMod.addRelic(new FoldingChair(), RelicType.RED);
            BaseMod.addRelic(new Emberfly(), RelicType.RED);
        }

        if (enableSilentRelics) {
            BaseMod.addRelic(new AntiAntidote(), RelicType.GREEN);
            BaseMod.addRelic(new Windchimes(), RelicType.GREEN);
            BaseMod.addRelic(new PorcelainTeacup(), RelicType.GREEN);
            BaseMod.addRelic(new CoiledNail(), RelicType.GREEN);
        }

        if (enableDefectRelics) {
            BaseMod.addRelic(new BlackMirror(), RelicType.BLUE);
            BaseMod.addRelic(new GlassCog(), RelicType.BLUE);
            BaseMod.addRelic(new ColdBattery(), RelicType.BLUE);
            BaseMod.addRelic(new TuningFork(), RelicType.BLUE);
        }

        if (enableWatcherRelics) {
            BaseMod.addRelic(new WolfsFang(), RelicType.PURPLE);
            BaseMod.addRelic(new FadedBookmark(), RelicType.PURPLE);
            BaseMod.addRelic(new Bonsai(), RelicType.PURPLE);
            BaseMod.addRelic(new EmperorsAnkh(), RelicType.PURPLE);
        }

        logger.info("Done adding relics!");
    }

    
    @Override
    public void receiveEditStrings() {
        logger.info("Beginning to edit strings for mod with ID: " + getModID());

        // PowerStrings
        BaseMod.loadCustomStringsFile(PowerStrings.class,
                getModID() + "Resources/localization/eng/MoreClassRelics-Power-Strings.json");

        // RelicStrings
        BaseMod.loadCustomStringsFile(RelicStrings.class,
                getModID() + "Resources/localization/eng/MoreClassRelics-Relic-Strings.json");
        
        logger.info("Done editting strings");
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal(getModID() + "Resources/localization/eng/MoreClassRelics-Keyword-Strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);
        
        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(getModID().toLowerCase(), keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

    public static String makeID(String idText) {
        return getModID() + ":" + idText;
    }
}
