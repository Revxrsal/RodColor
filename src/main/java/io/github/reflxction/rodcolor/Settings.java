/*
 * * Copyright 2018 github.com/ReflxctionDev
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
package io.github.reflxction.rodcolor;

/**
 * A class with all settings as constants
 */
public class Settings {

    /**
     * RGB settings
     */
    public static final Setting<Integer> RED = new Setting<>("Red", 0);
    public static final Setting<Integer> GREEN = new Setting<>("Green", 0);
    public static final Setting<Integer> BLUE = new Setting<>("Blue", 0);
    public static final Setting<Integer> ALPHA = new Setting<>("Alpha", 255);


    /**
     * Represents a setting
     *
     * @param <T> Setting type
     */
    public static class Setting<T> {

        // The actual value of the setting
        private Object value;

        // The config category of the setting
        private String category;

        // The config name of the setting
        private String configName;

        // The default value of the setting.
        private Object defaultValue;

        /**
         * Initiates a new Setting
         *
         * @param category     Category name
         * @param configName   Config name
         * @param defaultValue Default value of the setting
         */
        private Setting(String category, String configName, Object defaultValue) {
            this.category = category;
            this.configName = configName;
            this.defaultValue = defaultValue;
            if (defaultValue instanceof Boolean)
                value = RodColor.getConfig().get(category, configName, (boolean) defaultValue).getBoolean();
            else if (defaultValue instanceof Integer)
                value = RodColor.getConfig().get(category, configName, (int) defaultValue).getInt();
            else if (defaultValue instanceof Double)
                value = RodColor.getConfig().get(category, configName, (double) defaultValue).getDouble();
            else if (defaultValue instanceof String)
                value = RodColor.getConfig().get(category, configName, (String) defaultValue).getString();
        }

        /**
         * Initiates a new setting in the "Settings" category
         *
         * @param configName   Category name
         * @param defaultValue Default value of the setting
         */
        private Setting(String configName, Object defaultValue) {
            this("Settings", configName, defaultValue);
        }

        /**
         * Returns the value of the setting
         *
         * @return The value of the setting
         */
        public T get() {
            //noinspection unchecked
            return (T) value;
        }

        /**
         * Sets the value of the setting and updates in the config
         *
         * @param value Value to set
         */
        public void set(T value) {
            this.value = value;
            if (value instanceof Boolean)
                RodColor.getConfig().get(category, configName, (Boolean) defaultValue).set((Boolean) value);
            else if (value instanceof Integer)
                RodColor.getConfig().get(category, configName, (Integer) defaultValue).set((Integer) value);
            else if (value instanceof Double)
                RodColor.getConfig().get(category, configName, (Double) defaultValue).set((Double) value);
            else if (value instanceof String)
                RodColor.getConfig().get(category, configName, (String) defaultValue).set((String) value);
            else
                RodColor.getConfig().get(category, configName, defaultValue.toString()).set(value.toString());
        }
    }
}
