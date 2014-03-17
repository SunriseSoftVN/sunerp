/**
 * Created by dungvn3000 on 3/17/14.
 */
Ext.define('sunerp.model.CompanySetting', {
    extend: 'sunerp.model.BaseModel',
    fields: [
        'id',
        'companyId',
        'company',
        'luongToiThieu'
    ],
    associations: [
        {type: 'belongsTo', model: 'sunerp.model.Company', name: 'company'}
    ]
});
