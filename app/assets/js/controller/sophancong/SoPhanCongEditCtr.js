/**
 * Created by dungvn3000 on 3/11/14.
 */

Ext.define('sunerp.controller.sophancong.SoPhanCongEditCtr', {
    extend: 'sunerp.controller.core.BaseEditController',
    inject: ['soPhanCongStore'],
    config: {
        soPhanCongStore: null
    },
    init: function() {
        this.mainStore = this.getSoPhanCongStore();
        this.callParent(arguments);
    }
});