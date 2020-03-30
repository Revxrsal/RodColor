/*
 * * Copyright 2019-2020 github.com/ReflxctionDev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.reflxction.rodcolor.gui;

import io.github.reflxction.rodcolor.Settings.Setting;
import net.minecraft.client.gui.GuiPageButtonList.GuiResponder;

public class SliderResponder implements GuiResponder {

    private Setting<Integer> setting;

    public SliderResponder(Setting<Integer> setting) {
        this.setting = setting;
    }

    /**
     * setEntryValue
     */
    @Override
    public void func_175321_a(int id, boolean b) {

    }

    /**
     * setEntryValue
     */
    @Override
    public void onTick(int id, float v) {
        setting.set((int) v);
    }

    /**
     * setEntryValue
     */
    @Override
    public void func_175319_a(int id, String s) {

    }
}
