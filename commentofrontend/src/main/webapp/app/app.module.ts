import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { CodetestfrontendSharedModule } from 'app/shared/shared.module';
import { CodetestfrontendCoreModule } from 'app/core/core.module';
import { CodetestfrontendAppRoutingModule } from './app-routing.module';
import { CodetestfrontendHomeModule } from './home/home.module';
import { CodetestfrontendEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    CodetestfrontendSharedModule,
    CodetestfrontendCoreModule,
    CodetestfrontendHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    CodetestfrontendEntityModule,
    CodetestfrontendAppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [MainComponent],
})
export class CodetestfrontendAppModule {}
