/*
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.dashbuilder.displayer;

import org.dashbuilder.dataset.DataSet;
import org.dashbuilder.dataset.DataSetLookupBuilder;

/**
 * A DisplayerSettingsBuilder allows for the assembly of a DisplayerSettings instance in a friendly manner.
 *
 * <pre>
 *   DisplayerSettingsFactory.newBarChartSettings()
 *   .title("By Product")
 *   .titleVisible(false)
 *   .margins(10, 50, 100, 100)
 *   .column("Product")
 *   .column("Total amount")
 *   .horizontal()
 *   .buildSettings();
 * </pre>
 *
 * @see DisplayerSettings
 */
public interface DisplayerSettingsBuilder<T> extends DataSetLookupBuilder<T> {

    /**
     * Set the DisplayerSettings' UUID.
     *
     * @param uuid The UUID of the DisplayerSettings that is being assembled.
     * @return The DisplayerSettingsBuilder instance that is being used to configure a DisplayerSettings.
     */
    T uuid(String uuid);

    /**
     * Set a direct reference to the source data set that will be used by the Displayer that is being assembled.
     * <p>When using this <i>dataset provided mode</i> the data set lookup operations set (if any): filter, group & sort  will not be taking into account).
     *
     * @return The DisplayerSettingsBuilder instance that is being used to configure a DisplayerSettings.
     * @see org.dashbuilder.dataset.DataSet
     */
    T dataset(DataSet dataSet);

    /**
     * Sets the caption that will be shown for this particular visualization of the data.
     * @param title The caption to be shown
     * @return The DisplayerSettingsBuilder instance that is being used to configure a DisplayerSettings.
     */
    T title(String title);

    /**
     * Set whether the caption should be visible or not.
     * @param visible True if the caption is to be visible, false if not.
     * @return The DisplayerSettingsBuilder instance that is being used to configure a DisplayerSettings.
     */
    T titleVisible(boolean visible);

    /**
     * Set the background color for the displayer. 
     * @param backgroundColor The background color code.
     * @return The DisplayerSettingsBuilder instance that is being used to configure a DisplayerSettings.
     */
    T backgroundColor(String backgroundColor);
    
    /**
     * Set the renderer that will be used for visualizing this DisplayerSettings.
     * @param renderer The identifier of the renderer.
     * @return The DisplayerSettingsBuilder instance that is being used to configure a DisplayerSettings.
     */
    T renderer(String renderer);

    /**
     * Enable the ability to select/filter values (or range of values) within the data displayer.
     *
     * <p> Usually, in a dashboard there exists a unique coordinator which takes cares of propagate all the data
     * selection events among the other displayers. If enabled then there exists also the ability to configure how to
     * interact with other displayers in the same dashboard.</p>

     * @param applySelf If true then any filter request issued within the data displayer will be applied to the own displayer.
     * @param notifyOthers If true then any filter request issued within the data displayer will be propagated to other interested displayers.
     * @param receiveFromOthers If true then the data displayer will listen for filter requests coming from other displayers.
     *
     * @return The DisplayerSettingsBuilder instance that is being used to configure a DisplayerSettings.
     */
    T filterOn(boolean applySelf, boolean notifyOthers, boolean receiveFromOthers);

    /**
     * Disable the ability to select/filter values (or range of values) within the displayer.
     *
     * @param receiveFromOthers If true then the data displayer will listen for filter requests coming from other displayers.
     * @see DisplayerSettingsBuilder#filterOn DisplayerSettingsBuilder's filterOn method.
     * @return The DisplayerSettingsBuilder instance that is being used to configure a DisplayerSettings.
     */
    T filterOff(boolean receiveFromOthers);

    /**
     * Force the displayer to redraw only when data becomes stale.
     *
     * @return The DisplayerSettingsBuilder instance that is being used to configure a DisplayerSettings.
     */
    T refreshOn();

    /**
     * Force the displayer to redraw every time interval.
     *
     * @param seconds The refresh time frame in seconds. If < 0 then periodic refresh is disabled.
     * @param onStale Refresh when the data becomes stale.
     *
     * @return The DisplayerSettingsBuilder instance that is being used to configure a DisplayerSettings.
     */
    T refreshOn(int seconds, boolean onStale);

    /**
     * Switch off the automatic refresh.
     *
     * @see DisplayerSettingsBuilder#refreshOn DisplayerSettingsBuilder's refreshOn method.
     * @return The DisplayerSettingsBuilder instance that is being used to configure a DisplayerSettings.
     */
    T refreshOff();

    /**
     * Defines the display name for the last specified data set column.
     *
     * NOTE: This method can only be called right after a call to <i>DataSetLookupBuilder#column(...)</i>.
     *
     * @param name The column display name.
     * @return The DisplayerSettingsBuilder instance that is being used to configure a DisplayerSettings.
     */
    T format(String name);

    /**
     * Defines the display format for the last specified data set column.Every data set value will be formatted
     * according to the specified <i>pattern</i> parameter which defines the string format of the data set value.
     * Examples:
     * <ul>
     *     <li>format("Amount", "$ #,###.##") => "$ 10,450.5"</li>
     *     <li>format("Amount", "$ #,### K") => "$ 450 K"</li>
     * </ul>
     *
     * NOTE: This method can only be called right after a call to <i>DataSetLookupBuilder#column(...)</i>.
     *
     * @param name The column display name.
     * @param pattern The standard java <i>DecimalFormat</i> and <i>DateFormat</i> can be used used for both number and date columns.
     * @see java.text.DecimalFormat
     * @see java.text.SimpleDateFormat
     * @return The DisplayerSettingsBuilder instance that is being used to configure a DisplayerSettings.
     */
    T format(String name, String pattern);

    /**
     * Defines the display format for the specified data set column. Every data set value will be formatted
     * according to the specified <i>pattern</i> parameter which defines the string format of the data set value.
     * Examples:
     * <ul>
     *     <li>format("Amount", "$ #,###.##") => "$ 10,450.5"</li>
     *     <li>format("Amount", "$ #,### K") => "$ 450 K"</li>
     * </ul>
     *
     * @param columnId The column identifier.
     * @param name The column display name.
     * @param pattern The standard java <i>DecimalFormat</i> and <i>DateFormat</i> are used for both number and date columns.
     * @see java.text.DecimalFormat
     * @see java.text.SimpleDateFormat
     * @return The DisplayerSettingsBuilder instance that is being used to configure a DisplayerSettings.
     */
    T format(String columnId, String name, String pattern);

    /**
     * Defines a mathematical expression used to calculate the real values to display for the last specified data set
     * column.
     *
     * <p>For numeric columns the expression can be any basic math expression where the <i>value</i> keyword represent
     * the source data set value and any of the basic math operators "/ * + -" are allowed. Examples:
     * <ul>
     *     <li>format("Amount", "$ #,###.##").expression("value") => "$ 10,450.5"</li>
     *     <li>format("Amount", "$ #,###.##").expression("value-1") => "$ 10,449.5"</li>
     *     <li>format("Amount", "$ #,##0.00 K").expression("value/1000") => "$ 10.45 K"</li>
     * </ul>
     *
     * <p> For text columns you can manipulate the string using any javascript statement: Examples</p>
     * <ul>
     *     <li>format("Quarter").expression("["1st Q", "2nd Q", "3rd Q", "4th Q"][value+1]") => "3rd Q" (value=1 in a date fixed grouped column)</li>
     *     <li>format("3 chars").expression("value.substring(0, 3) + "...") => "Dav..." it takes the first 3 chars only</li>
     * </ul>
     *
     * NOTE: This method can only be called right after a call to <i>DataSetLookupBuilder#column(...)</i>
     *
     * @param expression The expression used to calculate the value to display
     * @return The DisplayerSettingsBuilder instance that is being used to configure a DisplayerSettings.
     */
    T expression(String expression);

    /**
     * Sames as <i>expression(String expression)</i> but using the specified column.
     *
     * @param columnId The column identifier.
     * @param expression The expression used to calculate the value to display
     * @return The DisplayerSettingsBuilder instance that is being used to configure a DisplayerSettings.
     */
    T expression(String columnId, String expression);

    /**
     * @return The DisplayerSettings instance that has been configured.
     * @see DisplayerSettings
     */
    DisplayerSettings buildSettings();
}
