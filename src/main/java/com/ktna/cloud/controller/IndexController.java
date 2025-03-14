package com.ktna.cloud.controller;

import lombok.RequiredArgsConstructor;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.core.handle.Context;

@Controller
@RequiredArgsConstructor
public class IndexController {

    @Mapping("/")
    public void home(Context context) {
        context.forward("/index.html");
    }

}