package me.eparon.screens;
import me.eparon.mods.DisplayFPS;
import me.eparon.StoicClient;
import me.eparon.themes.Default;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.toasts.SystemToast;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.components.SplashRenderer;
// :::1
public class mainGui extends Screen {
    // :::1
    // :::2



    public Screen parent;
    public mainGui(Component title, Screen parent) {
        super(title);
        this.parent = parent;
    }

//    @Override
//    public void onClose() {
//        this.minecraft.setScreen(this.parent);
//    }

    // :::2
    // :::1
    public mainGui(Component title) {
        super(title);
    }

    @Override
    protected void init() {
        // x, y, width, height
        // It's recommended to use the fixed height of 20 to prevent rendering issues with the button
        // textures.

        this.addRenderableWidget(DisplayFPS.displayFPS);


        // Add a custom widget to the screen.
        // x, y, width, height
        //CustomWidget customWidget = new CustomWidget(40, 80, 120, 20);
        //this.addRenderableWidget(customWidget);
    }
    public void handleWidgetPreview(CustomWidget w){
        if(w.displayHovered && w.removedWidget){
            this.addRenderableWidget(w.hovered);
            w.removedWidget = false;
        }
        else if(!w.removedWidget && !w.displayHovered){

            this.removeWidget(w.hovered);
            w.removedWidget = true;
        }
    }
    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        Minecraft client = Minecraft.getInstance();
        super.render(graphics, mouseX, mouseY, delta);


        handleWidgetPreview(DisplayFPS.displayFPS);


        graphics.drawString(minecraft.font,
                "Stoic Client",
                this.width / 2 - minecraft.font.width("Stoic Client") /2 ,
                20,
                Default.WIDGETCOLOR,
                true
        );



    }
    public int getWidth(){
        return parent.width;
    }
}
// :::1