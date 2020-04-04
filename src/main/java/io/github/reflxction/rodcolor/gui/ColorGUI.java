package io.github.reflxction.rodcolor.gui;

import io.github.reflxction.rodcolor.RodColor;
import io.github.reflxction.rodcolor.Settings;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlider;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;

import static io.github.reflxction.rodcolor.Settings.*;

@SideOnly(Side.CLIENT)
public class ColorGUI extends GuiScreen {

    private GuiSlider sliderRed = new GuiSlider(new SliderResponder(RED), 2, this.width / 2 - 70, this.height / 2 - 80, "Red", 0, 255, RED.get(), SliderFormatHelper.HELPER);
    private GuiSlider sliderGreen = new GuiSlider(new SliderResponder(Settings.GREEN), 3, this.width / 2 - 70, this.height / 2 - 58, "Green", 0, 255, Settings.GREEN.get(), SliderFormatHelper.HELPER);
    private GuiSlider sliderBlue = new GuiSlider(new SliderResponder(Settings.BLUE), 4, this.width / 2 - 70, this.height / 2 - 36, "Blue", 0, 255, Settings.BLUE.get(), SliderFormatHelper.HELPER);
    private GuiSlider sliderAlpha = new GuiSlider(new SliderResponder(Settings.ALPHA), 5, this.width / 2 - 70, this.height / 2 - 14, "Alpha", 0, 255, Settings.ALPHA.get(), SliderFormatHelper.HELPER);
    private GuiButton buttonChroma = new GuiButton(6, this.width / 2 - 70, this.height / 2 + 8, 150, 20, "Chroma: " + (CHROMA.get() ? EnumChatFormatting.RED + "Disable" : EnumChatFormatting.GREEN + "Enable"));

    public void initGui() {
        this.buttonList.clear();
        sliderRed.yPosition = this.height / 2 - 80;
        sliderGreen.yPosition = this.height / 2 - 58;
        sliderBlue.yPosition = this.height / 2 - 36;
        sliderAlpha.yPosition = this.height / 2 - 14;
        buttonChroma.yPosition = this.height / 2 + 8;
        super.buttonList.add(sliderRed);
        super.buttonList.add(sliderGreen);
        super.buttonList.add(sliderBlue);
        super.buttonList.add(sliderAlpha);
        super.buttonList.add(buttonChroma);
        buttonList.forEach(b -> b.xPosition = this.width / 2 - 70);
    }

    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        try {
            this.drawDefaultBackground();
        } catch (Exception ignored) { // literally it happens for no absolute reason...
        }
        int x = this.width / 2 - 70;
        int y = this.height / 2 + 15;
        long dif = (x * 10) - (y * 10);
        long color = System.currentTimeMillis() - dif;
        float ff = 2000.0F;
        int i = Color.HSBtoRGB((float) (color % (int) ff) / ff, 0.8F, 0.8F);
        if (CHROMA.get())
            drawRect(x - 30, y + 22, x + 180, y + 32, i);
        else
            drawRect(x - 30, y + 22, x + 180, y + 32, new Color(RED.get(), GREEN.get(), BLUE.get(), ALPHA.get()).getRGB());
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public void actionPerformed(final GuiButton button) {
        switch (button.id) {
            case 2: {
                RED.set(getSliderValue(sliderRed));
                break;
            }
            case 3: {
                Settings.GREEN.set(getSliderValue(sliderGreen));
                break;
            }
            case 4: {
                Settings.BLUE.set(getSliderValue(sliderBlue));
                break;
            }
            case 5: {
                Settings.ALPHA.set(getSliderValue(sliderAlpha));
                break;
            }
            case 6: {
                boolean chroma = CHROMA.set(!CHROMA.get());
                button.displayString = "Chroma: " + (chroma ? EnumChatFormatting.RED + "Disable" : EnumChatFormatting.GREEN + "Enable");
            }
        }
    }

    public void mouseClickMove(final int mouseX, final int mouseY, final int clickedMouseButton, final long timeSinceLastClick) {
        RED.set(getSliderValue(sliderRed));
        Settings.GREEN.set(getSliderValue(sliderGreen));
        Settings.BLUE.set(getSliderValue(sliderBlue));
        Settings.ALPHA.set(getSliderValue(sliderAlpha));
    }

    public void onGuiClosed() {
        RodColor.getConfig().save();
    }

    private int getSliderValue(GuiSlider slider) {
        return (int) slider.func_175220_c(); // apparently this is the getSliderValue() method in 1.12.2
    }

}
