import { Component, OnInit, OnDestroy, ViewChild } from '@angular/core';
import { Subscription } from 'rxjs';

import { LoginModalService } from 'app/core/login/login-modal.service';
import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/user/account.model';
import { IPost } from 'app/shared/model/post.model';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { PostService } from 'app/entities/post/post.service';
import { CommentsService } from 'app/entities/comments/comments.service';

@Component({
  selector: 'jhi-home',
  templateUrl: './home.component.html',
  styleUrls: ['home.scss'],
})
export class HomeComponent implements OnInit, OnDestroy {
  account: Account | null = null;
  authSubscription?: Subscription;
  posts: IPost[] = [];
  public initial = 0;
  // @ViewChild('grid')
  // public postGrid?: GridComponent;
  // public pageSettings: Object = {pageSizes: false, pageSize: 10};
  // public toolbar: Object[] =   ['Search'];
  //

  constructor(
    private accountService: AccountService,
    private loginModalService: LoginModalService,
    private postService: PostService,
    private commentService: CommentsService
  ) {}

  ngOnInit(): void {
    this.loadData();
    this.authSubscription = this.accountService.getAuthenticationState().subscribe(account => (this.account = account));
  }

  loadData(): void {
    this.postService.query().subscribe((res: HttpResponse<IPost[]>) => this.onSucess(res.body, res.headers));
  }

  isAuthenticated(): boolean {
    return this.accountService.isAuthenticated();
  }

  login(): void {
    this.loginModalService.open();
  }

  ngOnDestroy(): void {
    if (this.authSubscription) {
      this.authSubscription.unsubscribe();
    }
  }
  private onSucess(data: any, headers: any): void {
    this.posts = data;
  }
}
