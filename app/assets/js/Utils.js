/**
 * Created by dungvn3000 on 4/10/14.
 */

Ext.define('sunerp.Utils', {
    statics: {
        /**
         * Convert a value to a string, if the value is null return null also.
         * @param value
         * @returns {*}
         */
        toString: function (value) {
            if (value) {
                return String(value);
            } else {
                return null;
            }
        }
    }
});