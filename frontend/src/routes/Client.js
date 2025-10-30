
import Home from "../layout/client/pagehome/Home.vue"
import Detail from "../layout/client/detail/Detail.vue"
import Categories from "../layout/client/category/Categories.vue"
import Blog from "../layout/client/blog/Blog.vue"
import BlogDetail from "../layout/client/blogdetail/BlogDetail.vue"
import ContentDetail from "../layout/client/blogdetail/ContentDetail.vue"
import AuthorAll from "../layout/client/author/AuthorAll.vue"
import AuthorDetail from "../layout/client/author/AuthorDetail.vue"
import AboutUs from "../layout/client/header/aboutus/AboutUs.vue"
import OurTeam from "../layout/client/header/ourteam/OurTeam.vue"
import DescribeAuthor from "../layout/client/header/detailauthor/DescribeAuthor.vue"
import Contacts from "../layout/client/header/contacts/Contacts.vue"
import Home2 from "../layout/client/allpage/home/Home2.vue"
import AllBooks from "../layout/client/bookstore/AllBooks.vue"
import CategoryPage from "../layout/client/category/CategoryPage.vue"
import Checkout from "../layout/client/cart/Checkout.vue"
import FavoritesView from "../layout/client/cart/FavoritesView.vue"
import ReadBook from "../layout/client/detail/detailproduct/ReadBook.vue"
import Client from "../layout/client/client.vue"
import MainHome from "../layout/client/main/MainHome.vue"
import PackagesBook from "../layout/client/allpage/home/PackagesBook.vue"
import LoginAdmin from "../layout/client/LoginAdmin.vue"
export default [
  {
    path: '/',
    component: Client,
    children: [
      { path: '', component: MainHome },
      { path: 'home2', component: Home2 },
      { path: 'home3', component: Home },
      { path: 'about', component: Home },

      { path: 'book', component: AllBooks },
      { path: 'category', component: CategoryPage },

      { path: 'favorites/:id', component: ReadBook },
      { path: 'favorites', component: FavoritesView },

      { path: 'service/:name/:type', component: Blog },
      { path: 'blog/:name/:type', component: BlogDetail },
      { path: 'content/:name', component: ContentDetail },

      { path: 'author', component: AuthorAll },
      { path: 'author/detail', component: AuthorDetail },
      { path: 'About/:type', component: AboutUs },
      { path: 'our/:type', component: OurTeam },
      { path: 'team', component: DescribeAuthor },
      { path: 'packages', component: PackagesBook },
      // ⚠️ Đưa các dynamic route cuối cùng
      { path: 'book/:name/:type/:id', component: Detail },
      { path: 'contact/:type', component: Contacts },
    ],
  },
  {
    path: "/admin/login",
    component: LoginAdmin
  }
];
