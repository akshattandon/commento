import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IComments, Comments } from 'app/shared/model/comments.model';
import { CommentsService } from './comments.service';

@Component({
  selector: 'jhi-comments-update',
  templateUrl: './comments-update.component.html',
})
export class CommentsUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    email: [null, [Validators.required, Validators.pattern('\\A[a-zA-Z+_.-]+@[a-zA-Z.-]+\\Z')]],
    body: [null, [Validators.required]],
  });

  constructor(protected commentsService: CommentsService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ comments }) => {
      this.updateForm(comments);
    });
  }

  updateForm(comments: IComments): void {
    this.editForm.patchValue({
      id: comments.id,
      name: comments.name,
      email: comments.email,
      body: comments.body,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const comments = this.createFromForm();
    if (comments.id !== undefined) {
      this.subscribeToSaveResponse(this.commentsService.update(comments));
    } else {
      this.subscribeToSaveResponse(this.commentsService.create(comments));
    }
  }

  private createFromForm(): IComments {
    return {
      ...new Comments(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      email: this.editForm.get(['email'])!.value,
      body: this.editForm.get(['body'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IComments>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
