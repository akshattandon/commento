import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'post',
        loadChildren: () => import('./post/post.module').then(m => m.CodetestfrontendPostModule),
      },
      {
        path: 'comments',
        loadChildren: () => import('./comments/comments.module').then(m => m.CodetestfrontendCommentsModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class CodetestfrontendEntityModule {}
