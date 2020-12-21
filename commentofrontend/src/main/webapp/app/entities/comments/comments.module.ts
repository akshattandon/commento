import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CodetestfrontendSharedModule } from 'app/shared/shared.module';
import { commentsRoute } from './comments.route';

@NgModule({
  imports: [CodetestfrontendSharedModule, RouterModule.forChild(commentsRoute)],
  declarations: [],
  entryComponents: [],
})
export class CodetestfrontendCommentsModule {}
