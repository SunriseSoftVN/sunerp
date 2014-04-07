/**
 * Created by dungvn3000 on 3/14/14.
 * The model automatic update mapping field when set associations table.
 */

Ext.define('sunerp.model.BaseModel', {
    extend: 'Ext.data.Model',
    /**
     * Use with care, performance is slow.
     * @param fieldName
     * @param newValue
     */
    setAssociationsValue: function (fieldName, newValue) {
        var me = this;
        if (newValue != null && Ext.isObject(newValue)) {
            for (var key in newValue) {
                me.set(fieldName + "." + key, newValue[key]);
            }
            me.set(fieldName + 'Id', newValue.id);
        }
    }
});