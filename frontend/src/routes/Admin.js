import AccountManagement from "../layout/admin/AccountManagement.vue";
import AdminLayout from "../layout/admin/AdminLayout.vue";
import CreateAdminAccount from "../layout/admin/CreateAdminAccount.vue";
import BookMenu from "../layout/admin/library/BookMenu.vue";
import CreateBook from "../layout/admin/library/CreateBook.vue";
import EditBook from "../layout/admin/library/EditBook.vue";
import Permissions from "../layout/admin/permissions/Permissions.vue";



export default [
    {
        path: "/admin",
        component: AdminLayout,
        children: [
            {
                path: "library",
                component: BookMenu
            },
            {
                path: "create",
                component: CreateBook
            },
            {
                path: "edit/:id",
                component: EditBook
            },
            {
                path: "permissions",
                component: Permissions
            }, 
            {
                path: "account",
                component: AccountManagement
            }, 
            {
                path: "createadmin",
                component: CreateAdminAccount
            }
        ]
    }
]