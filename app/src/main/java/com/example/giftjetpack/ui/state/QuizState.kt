package com.example.giftjetpack.ui.state

data class QuizkUiState(
  val currQuestion: Int? = null,
  val questionListIndexes: MutableList<Int> = mutableListOf(),
)