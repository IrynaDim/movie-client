package com.dev.movie.client.telegram.utils;

import com.vdurmont.emoji.EmojiParser;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Emoji {
    WAVE(EmojiParser.parseToUnicode(":wave:")),
    CLAPPER(EmojiParser.parseToUnicode(":clapper:")),
    PENSIVE(EmojiParser.parseToUnicode(":pensive:")),
    OK_HAND(EmojiParser.parseToUnicode(":ok_hand:")),
    SMILING_FACE(EmojiParser.parseToUnicode(":blush:"));

    private final String emojiName;


    @Override
    public String toString() {
        return emojiName;
    }
}
